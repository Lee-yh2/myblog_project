package com.lyhcoding.myblog.core.exception.ssr;

import com.lyhcoding.myblog.dto.ResponseDTO;
import com.lyhcoding.myblog.dto.ValidDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 유효성 검사 실패, 잘못된 파라메터 요청
@Getter
public class Exception400 extends RuntimeException {

    private String key;
    private String value;

    public Exception400(String key, String value) {
        super(value);
        this.key = key;
        this.value = value;
    }

    public ResponseDTO<?> body(){
        ResponseDTO<ValidDTO> responseDto = new ResponseDTO<>();
        ValidDTO validDTO = new ValidDTO(key, value);
        responseDto.fail(HttpStatus.BAD_REQUEST, "badRequest", validDTO);
        return responseDto;
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}