$(function(){
    event();
});


function event(){
    $('#login_btn').click(function (){
        doLogin();
    });
}


function doLogin(){

    let data = {
        name:$("#name").val(),
        password: $("#password").val()
    }

    if (data.name.length<2 || data.password.length<4){
        alert("이름, 비밀번호를 정확하게 입력하십시오.");
        return;
    }

    postAjax("session/login", data, doLogin_successFunc);
}

function doLogin_successFunc(data){
    if(data.success){
        location.replace("/");
    }else{
        alert(data.errorMessage);
    }
}
