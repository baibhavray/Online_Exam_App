<?php
	$name = $_POST['name'];
	$emailid = $_POST['emailid'];
	$mobileno = $_POST['mobileno'];
	$address = $_POST['address'];
	$gender = $_POST['gender'];


	$con = mysqli_connect("localhost","root","","onlineexam");
	if(!$con){	
		echo "Database Not Found";
	}
	$sql1 = "select * from students where emailid='$emailid'";
	$result1 = mysqli_query($con,$sql1);
	if(mysqli_num_rows($result1)>0){	
		$sql2 = "update students set name='$name', mobileno='$mobileno', address='$address', gender='$gender' where emailid='$emailid'";
		$status = mysqli_query($con,$sql2);
		if($status){	
			echo "Profile Updated";
		}
	}else{
		echo "Invalid Current Password, Try Again";
	}
?>
