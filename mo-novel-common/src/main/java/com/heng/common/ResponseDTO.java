package com.heng.common;

import lombok.Data;

@Data
public class ResponseDTO {
    private Integer code;
    private String msg;
    private Object data;

    public static ResponseDTO succ(Object data)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMsg(Constants.MESSAGE_OK);
        responseDTO.setData(data);
        responseDTO.setCode(Constants.STATUS_OK);
        return responseDTO;
    }

    public static ResponseDTO succ(String mess, Object data)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMsg(mess);
        responseDTO.setData(data);
        responseDTO.setCode(Constants.STATUS_OK);
        return responseDTO;
    }

    public static ResponseDTO fail(String mess)
    {
        ResponseDTO m = new ResponseDTO();
        m.setCode(Constants.STATUS_FAIL);
        m.setData(null);
        m.setMsg(mess);
        return m;
    }


    public static ResponseDTO fail(String mess, Object data)
    {
        ResponseDTO m = new ResponseDTO();
        m.setCode(Constants.STATUS_FAIL);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
}
