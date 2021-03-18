<?php
	$con = mysqli_connect("localhost","root","","onlineexam");
	if(!$con){	
		echo "Database Not Found";
	}
	$products = array(); 
	$sql = "select * from slider";
	$result = mysqli_query($con,$sql);
	while($row = mysqli_fetch_array($result)){
		$temp['imageurl'] = "http://192.168.0.102/android/images/".$row['image'];
		array_push($products,$temp);
	}
	echo json_encode($products);
?>