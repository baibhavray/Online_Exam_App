<form action="result.php" method="post">
<table>
<?php
	$con = mysqli_connect("localhost","root","","onlineexam");
	if(!$con){	
		echo "Database Not Found";
	}
	$sql = "select * from exam";
	$result = mysqli_query($con,$sql);
	while($row = mysqli_fetch_array($result)){
		$id = $row['id'];
		$question = $row['question'];
		$option1 = $row['option1'];
		$option2 = $row['option2'];
		$option3 = $row['option3'];
		$option4 = $row['option4'];
		$answer = $row['answer'];
		?>
		<tr>
			<td><?php echo $id.".".$question ?></td>
		</tr>
		<tr>
			<td><input type="radio" name="<?php echo $id; ?>" value="option1"><?php echo $option1 ?></td>
		</tr>
		<tr>
			<td><input type="radio" name="<?php echo $id; ?>" value="option2"><?php echo $option2 ?></td>
		</tr>
		<tr>
			<td><input type="radio" name="<?php echo $id; ?>" value="option3"><?php echo $option3 ?></td>
		</tr>
		<tr>
			<td><input type="radio" name="<?php echo $id; ?>" value="option4"><?php echo $option4 ?></td>
		</tr>
		<?php		
	}
?>
</table>
<br/>
<center>
	<h3><input type="submit" value="Submit Exam" style="background:#0000FF;color:white;width:200px;height:40px;"/></h3>
</center>
</form>