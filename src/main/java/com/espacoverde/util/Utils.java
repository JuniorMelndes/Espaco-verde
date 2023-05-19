package com.espacoverde.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.espacoverde.dao.ProductRepository;
import com.espacoverde.entity.Product;
import com.espacoverde.feature.exception.AlreadyExistsException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Utils {
	private final ProductRepository productRepository;
	
	/**
     * Lê uma planilha de Excel.
     *
     * @param sheetName O nome da planilha a ser lida.
     * @throws IOException Se ocorrer um erro de I/O durante a leitura da planilha.
     */
	public void readSheet(String sheetName) throws IOException {
		try (FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "\\planilha\\" + sheetName));
                XSSFWorkbook workbook = new XSSFWorkbook(file);) {
			Sheet sheet = workbook.getSheetAt(0);
			Row attributes = sheet.getRow(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Product newProduct = new Product();
				Row row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					switch(attributes.getCell(j).getStringCellValue()) {
						case "nome":
							newProduct.setName(cell.getStringCellValue());
							break;
						case "descricao":
							newProduct.setDescription(cell.getStringCellValue());
							break;
						case "quantidade":
							newProduct.setQuantity((long) cell.getNumericCellValue());
							break;
						case "disponibilidade":
							int aux = (int) cell.getNumericCellValue();
							newProduct.setAvailability(String.valueOf(aux));
							break;
						case "imagem":
							newProduct.setImage(imageToByteArray(cell.getStringCellValue()));
							break;
						default:
							System.out.println("Valor inválido");
					}
				}
				addNewProductFromSheet(newProduct);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Obtém a data e hora atual formatada.
     *
     * @return A data e hora atual formatada.
     */
	public String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

        String formattedTime = currentTime.format(formatter);

        return formattedTime;
    }
	
	/**
     * Verifica se um e-mail é válido.
     *
     * @param email O e-mail a ser verificado.
     * @return true se o e-mail for válido, false caso contrário.
     */
	public boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(emailPattern);

        return pattern.matcher(email).matches();
    }
	
	/**
     * Converte uma imagem em um array de bytes.
     *
     * @param imageName O nome da imagem.
     * @return O array de bytes da imagem.
     * @throws IOException Se ocorrer um erro de I/O durante a conversão da imagem.
     */
	public byte[] imageToByteArray(String imageName) throws IOException {
        if (imageName == null) {
			return null;
		} else {
			String pathImage = System.getProperty("user.dir") + "\\" + "images" + "\\" + imageName; 
			Path path = Paths.get(pathImage);
			return Files.readAllBytes(path);
		}
    }
	
	/**
     * Adiciona um novo produto a partir da planilha.
     *
     * @param newProduct O novo produto a ser adicionado.
     */
	private void addNewProductFromSheet(Product newProduct) {
		try {
			if (productRepository.findByName(newProduct.getName()) != null) {
				 throw new AlreadyExistsException("Produto com o nome '" + newProduct.getName() + "' já existente");
			}
			productRepository.save(newProduct);
		} catch (AlreadyExistsException e) {
			System.out.println(e);
		}
	}
}
