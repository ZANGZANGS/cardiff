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

    postAjax("session/login", data, doLogin_successFunc);
}

function doLogin_successFunc(data){
    if(data.success){
        location.replace("/");
    }else{
        alert(data.errorMessage);
    }
}
