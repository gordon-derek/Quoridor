cd testEndtoEnd2Player
bash testEndtoEnd2Player.sh
cd ..
cat testEndtoEnd2Player/testResults.txt > testResults.txt

cd testEndtoEnd4Player
bash testEndtoEnd4Player.sh
cd ..
cat testEndtoEnd4Player/testResults.txt >> testResults.txt

cd testEndtoEnd4PlayerRuleBreaks
bash testEndtoEnd4PlayerRuleBreaks.sh
cd ..
cat testEndtoEnd4PlayerRuleBreaks/testResults.txt >> testResults.txt

cd testEndtoEnd4PlayerWall
bash testEndtoEnd4PlayerWall.sh
cd ..
cat testEndtoEnd4PlayerWall/testResults.txt >> testResults.txt

