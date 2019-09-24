package com.ep.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ep.service.ExcelParseService;

@RestController
public class ExcelParserController {

	@Autowired
	private ExcelParseService epService;
	
	@PostMapping("/parse")
	public Object parse(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			String format = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			System.out.printf("File format is %s\n",format);
			
			switch(format) {
				case "xls":
					return epService.parseXLS(file.getInputStream());
				case "xlsx":
					return epService.parseXLSX(file.getInputStream());
				default:
					throw new RuntimeException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
}
