// FormDto.java (권장 변경)
package com.example.adSpy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FormDto {
    private String url;
    private String content;
}