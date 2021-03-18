		<?php
	//Get Data From Android App
	$name = $_POST['name'];
	$emailid = $_POST['emailid'];
	$password = $_POST['password'];
	$mobileno = $_POST['mobileno'];

	
	$con = mysqli_connect("localhost","root","","onlineexam");

	$sql1 = "select * from students where emailid='$emailid'";
	$result = mysqli_query($con,$sql1);

	if(mysqli_num_rows($result)>0){	
		echo "1";
	}else{	
		$sql = "insert into students values('$name','$emailid','$password','$mobileno')";		
		$status = mysqli_query($con,$sql);
		if($status){
			echo "2";
		}else{
			echo "3";
		}
	}
?>



