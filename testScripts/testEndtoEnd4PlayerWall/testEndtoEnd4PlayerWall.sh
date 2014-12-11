#!/bin/bash
cd ../../bin
(java quoridorServer.Server 3939 2 & )> ../testScripts/testEndtoEnd4PlayerWall/server1Test.txt
(java quoridorServer.Server 3838 2 & )> ../testScripts/testEndtoEnd4PlayerWall/server2Test.txt
(java quoridorServer.Server 4848 2 & )> ../testScripts/testEndtoEnd4PlayerWall/server3Test.txt
(java quoridorServer.Server 4949 2 & )> ../testScripts/testEndtoEnd4PlayerWall/server4Test.txt
java quoridorClient.Client localhost:3939 localhost:3838 localhost:4949 localhost:4848  > ../testScripts/testEndtoEnd4PlayerWall/clientTest.txt
cd ../testScripts/testEndtoEnd4PlayerWall
diff server1Test.txt server1TestCorrectOutput.txt > testResults.txt
diff server2Test.txt server2TestCorrectOutput.txt >> testResults.txt
diff server3Test.txt server3TestCorrectOutput.txt > testResults.txt
diff server4Test.txt server4TestCorrectOutput.txt >> testResults.txt
diff clientTest.txt clientTestCorrectOutput.txt >> testResults.txt
