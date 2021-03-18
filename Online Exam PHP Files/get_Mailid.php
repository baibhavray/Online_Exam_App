<?php
	//Get Data From Android App
	$emailid = 'a';
	$x='19';

	$con = mysqli_connect("localhost","root","","onlineexam");
	
	
	
	$sql1 = "select * from students";
	$result1 = mysqli_query($con,$sql1);

	$sql = "select * from students where emailid='$emailid'";
	$result = mysqli_query($con,$sql);
	if(mysqli_num_rows($result)>0){	
		$sql2 = "update students set  correct=concat(correct,',','$x') where emailid='$emailid'";
		$status = mysqli_query($con,$sql2);
	}
	
?>
