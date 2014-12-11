#!/bin/bash
cd ../../bin
(java quoridorServer.Server 3939 3 &) > ../testScripts/testEndtoEnd2Player/server1Test.txt
(java quoridorServer.Server 3838 7 &) > ../testScripts/testEndtoEnd2Player/server2Test.txt
java quoridorClient.Client localhost:3939 localhost:3838 > ../testScripts/testEndtoEnd2Player/clientTest.txt
cd ../testScripts/testEndtoEnd2Player
diff server1Test.txt server1TestCorrectOutput.txt > testResults.txt
diff server2Test.txt server2TestCorrectOutput.txt >> testResults.txt
diff clientTest.txt clientTestCorrectOutput.txt >> testResults.txt
