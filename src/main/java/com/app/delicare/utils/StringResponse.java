package com.app.delicare.utils;

public interface StringResponse {
    String OK = "SUCCESS";
    String FORBIDDEN = "FORBIDDEN";
    int ACTIVE = 1;
    int DEACTIVATE = 0;
    String NOT_EXIST_ID = "Id không tồn tại";
    String EXIST_ID = "Id đã tồn tại";
    String NOT_EXIST_RECORD = "Dữ liệu không tồn tại";



    //role
    String EXIST_ROLE = "Đã tồn tại role";
    String NOT_EXIST_ROLE = "Không tồn tại role";


    //action
    String EXIST_ACTION = "Đã tồn tại action này";

    //permission
    String DONT_HAVE_PERMISSION = "Bạn không có quyền thực hiện hành động này";
}
