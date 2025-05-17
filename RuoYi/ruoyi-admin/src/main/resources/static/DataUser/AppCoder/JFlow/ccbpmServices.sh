#!/bin/bash

# 定义变量
URL="http://localhost:8009/WF/API/DTS_ExecuteTimeTrigger"
LOG_FILE="/app/bpm/script/logfile.log"  # 日志文件路径
TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")

# 检查日志文件目录是否存在，不存在则创建
LOG_DIR=$(dirname "$LOG_FILE")
mkdir -p "$LOG_DIR"

# 日志记录函数
log_message() {
    echo "[$TIMESTAMP] $1" >> "$LOG_FILE"
    echo "$1"  # 可选：同时打印到终端，用于调试
}

# 执行curl请求的函数
execute_curl_request() {
    log_message "开始请求 $URL"
    curl -s -o /dev/null -w "%{http_code}\n" "$URL" 2>>"$LOG_FILE"
    STATUS_CODE=$?
    HTTP_STATUS=$(curl -s -o /dev/null -w "%{http_code}\n" "$URL" 2>>"$LOG_FILE")
    if [ "$HTTP_STATUS" -ne 200 ]; then
        log_message "请求失败，HTTP状态码：$HTTP_STATUS"
    else
        log_message "请求成功，HTTP状态码：$HTTP_STATUS"
    fi
    log_message "请求结束"
}

# 执行请求
execute_curl_request