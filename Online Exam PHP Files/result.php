<?php
	$con = mysqli_connect("localhost","root","","onlineexam");
	if(!$con){	
		echo "Database Not Found";
	}
	$sql = "select * from exam";
	$result = mysqli_query($con,$sql);



        $arr = array();
	$slnoarr = array();
        while($row1 = mysqli_fetch_array($result))
        {
                    $id = $row1['id'];
                    $ans1 = $row1['answer'];
                    $arr[$id] = $ans1;
	   array_push($slnoarr,$id);
        }
        $correct = 0;
        $wrong=0;
        $notattempt = 0;
        $total = 0;
        foreach($slnoarr as $v)
        {
	     $total++;
	     if(isset($_POST["$v"]))
	     {	
                  $ans = $_POST["$v"];
                  if($ans == $arr["$v"])
                  {
                            $correct++;
		
                  }
                  else
                  {
                            $wrong++;
                  }
             }
             else{
	     	    $notattempt++;
 		}
        }

	//$emailid = $_POST['emailid'];
	//$sql1 = "select * from students";
//	$result1 = mysqli_query($con,$sql1);
//	$sql = "select * from students where emailid='$emailid'";
//	$result = mysqli_query($con,$sql);
//	if(mysqli_num_rows($result)>0){	
//		$sql2 = "update students set  correct='5' where emailid='$emailid'";
//		$status = mysqli_query($con,$sql2);	
//	}

	

?>
<h1>Exam Report</h1><br/>
<h3>Total Questions : <?php echo $total; ?></h3>
<h3>Correct Answer : <?php echo $correct; ?></h3>
<h3>Wrong Answer : <?php echo $wrong; ?></h3>
<h3>Not Attempt: <?php echo $notattempt; ?></h3>


