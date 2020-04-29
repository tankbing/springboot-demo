#!/bin/bash
echo '' > aliyun
for ((i=1; i<=100; i ++))
do
    echo $i
    rm -rf /root/.m2/repository/*
    # start_mill=$((`date '+%s'`*1000+`date '+%N'`/1000000))
    mvn clean package -Dmaven.test.skip=true -s settings_ali.xml  >> aliyun
    # end_mill=$((`date '+%s'`*1000+`date '+%N'`/1000000))
    # diff=$[10#$end_mill-10#$start_mill]
    #echo $diff >> huawei
done
grep 'Total time' aliyun |awk 'BEGIN {max = 0} {if ($4>max) max=$4 fi} END {print "Max=", max}'
grep 'Total time' aliyun |awk 'BEGIN {min = 6553565} {if ($4<min) min=$4 fi} END {print "Min=", min}'
grep 'Total time' aliyun |awk '{sum+=$4}END{print "Average = ", sum/NR}'
