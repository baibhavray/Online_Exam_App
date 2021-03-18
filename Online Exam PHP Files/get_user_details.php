<?php
	$emailid = $_POST['emailid'];
	$con = mysqli_connect("localhost","root","","onlineexam");
	if(!$con){	
		echo "Database Not Found";
	}
	$users = array(); 
	$sql = "select * from students where emailid='$emailid'";
	$result = mysqli_query($con,$sql);
	$row = mysqli_fetch_array($result);	
	$temp['name'] = $row['name'];
	$temp['emailid'] = $row['emailid'];
	$temp['mobileno'] = $row['mobileno'];
	$temp['address'] = $row['address'];
	$temp['gender'] = $row['gender'];
	$temp['image'] = "http://192.168.0.102/android/images/".$row['image'];
		
	array_push($users,$temp);
	
	echo json_encode($users);
?>