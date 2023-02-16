#!/bin/bash

# 获取当前git commit-id
Previous_id=$(git rev-parse HEAD)
# 获取更新服务list

if [ $? -eq 0 ];then
git pull >> /dev/null 
dir=$(ls -l ./ |awk '/^d/ {print $NF}')
     commitd=$(git rev-parse HEAD)
     #commitd="18b8966dd213b376f2fe85fb880e1a0f3113c8e9"
     list_service=$(git diff $Previous_id $commitd --name-only | awk -F/ '{print $1}')
     if [ ! -n "$list_service" ];then
     	     echo "false"     
     else
        for list in $list_service
        do
           if [[ $dir == *$list* ]];then
             echo "gcex-contract-$list"  
           fi
        done
    
    fi
fi



