#!/bin/bash

# 指定要关闭的一系列端口
TARGET_PORTS=(8000 8100 8090 8070 8761 10010 8060 8050 8040 8120 8130 8030 8110 8020 8010 8140)

# 遍历数组并关闭相应的进程
for PORT in "${TARGET_PORTS[@]}"; do
  # 获取指定端口的进程ID（PID）
  PID=$(lsof -t -i:$PORT)

  if [ -z "$PID" ]; then
    echo "端口 $PORT 没有对应的进程在运行。"
  else
    # 终止指定进程
    kill -9 $PID
    echo "成功关闭端口 $PORT 的进程（PID: $PID）。"
  fi
done
