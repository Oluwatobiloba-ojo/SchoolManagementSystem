package com.example.quiz_application.services;

import com.example.quiz_application.config.BeanConfig;
import com.example.quiz_application.data.model.Quiz;
import com.example.quiz_application.data.model.Quiz_Question;
import com.example.quiz_application.exceptions.FileFormatException;
import static com.example.quiz_application.util.AppUtils.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class AppIExcelService implements IExcelService{
    @Autowired
    private BeanConfig beanConfig;

    @Override
    public void validate(MultipartFile file) throws FileFormatException {
        if (!Objects.equals(file.getContentType(), beanConfig.file_content_type))
            throw new FileFormatException(FILE_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Quiz_Question> convertFileToQuestion(InputStream inputStream, Quiz quiz)  {
        List<Quiz_Question> questions = new ArrayList<>();
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWorkbook.getSheet("FILL ME OUT");
            int rowIndex = 0;
            for (Row row : sheet){
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                Quiz_Question question = new Quiz_Question();

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> question.setQuiz(quiz);
                        case 1 -> question.setQuestion(cell.getStringCellValue());
                        case 2 -> question.setOptionA(cell.getStringCellValue());
                        case 3 -> question.setOptionB(cell.getStringCellValue());
                        case 4 -> question.setOptionC(cell.getStringCellValue());
                        case 5 -> question.setOptionD(cell.getStringCellValue());
                        case 6 -> question.setOptionE(cell.getStringCellValue());
                        case 7 -> question.setOptionF(cell.getStringCellValue());
                        case 8 -> question.setAnswer(cell.getStringCellValue());
                        default -> {}
                    }
                    cellIndex++;
                }
                if (question.getQuestion().isEmpty()) break;
                questions.add(question);
                rowIndex++;
            }
        } catch (IOException e) {
            throw new RuntimeException(FILE_INPUT_STREAM_NOT_FOUND);
        }
        return questions;
    }
}

