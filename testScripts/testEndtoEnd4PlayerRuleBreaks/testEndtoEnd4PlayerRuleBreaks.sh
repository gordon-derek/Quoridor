#!/bin/bash
cd ../../bin
(java quoridorServer.Server 3939 0 & )> ../testScripts/testEndtoEnd4PlayerRuleBreaks/server1Test.txt
(java quoridorServer.Server 3838 2 & )> ../testScripts/testEndtoEnd4PlayerRuleBreaks/server2Test.txt
(java quoridorServer.Server 4848 4 & )> ../testScripts/testEndtoEnd4PlayerRuleBreaks/server3Test.txt
(java quoridorServer.Server 4949 3 & )> ../testScripts/testEndtoEnd4PlayerRuleBreaks/server4Test.txt
java quoridorClient.Client localhost:3939 localhost:3838 localhost:4949 localhost:4848  > ../testScripts/testEndtoEnd4PlayerRuleBreaks/clientTest.txt
cd ../testScripts/testEndtoEnd4PlayerRuleBreaks
diff server1Test.txt server1TestCorrectOutput.txt > testResults.txt
diff server2Test.txt server2TestCorrectOutput.txt >> testResults.txt
diff server3Test.txt server3TestCorrectOutput.txt > testResults.txt
diff server4Test.txt server4TestCorrectOutput.txt >> testResults.txt
diff clientTest.txt clientTestCorrectOutput.txt >> testResults.txt
