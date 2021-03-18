<?php
	//Get Data From Android App
	$emailid = $_POST['emailid'];
	$password = $_POST['password'];

	
	$con = mysqli_connect("localhost","root","","onlineexam");
	$sql = "select * from students where emailid='$emailid' and password='$password' ";
		
	$result = mysqli_query($con,$sql);

	if(mysqli_num_rows($result) > 0){
		echo "valid";
	}else{
		echo "invalid";
	}
?>
