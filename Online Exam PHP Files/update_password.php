<?php
	$emailid = $_POST['emailid'];
	$curpassword = $_POST['curpassword'];
	$newpassword = $_POST['newpassword'];

	$con = mysqli_connect("localhost","root","","onlineexam");
	if(!$con){	
		echo "Database Not Found";
	}
	$sql1 = "select * from students where emailid='$emailid' and password='$curpassword'";
	$result1 = mysqli_query($con,$sql1);
	if(mysqli_num_rows($result1)>0){	
		$sql2 = "update students set password='$newpassword' where emailid='$emailid'";
		$status = mysqli_query($con,$sql2);
		if($status){	
			echo "Password Updated";
		}
	}else{
		echo "Invalid Current Password, Try Again";
	}
?>
