function viewLoader(){
    document.getElementById('formUpEx').style.display='none';
    document.getElementById('loader').style.display='block';
}
document.getElementById('buttSend').addEventListener("click", viewLoader);
