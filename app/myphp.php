<?php
   $con=mysqli_connect("","cs340_perezjoe","8666");
   $sql="INSERT INTO bobaway (cID) VALUES ('1')";
   if (mysqli_query($con,$sql)) {
      echo "Values have been inserted successfully";
   }
?>