#!/bin/bash
movieName=$1
readonly movieName
sourceDir="movie-source"
cd ${sourceDir}
mkdir ${movieName}
mv ${movieName}.flv ${movieName}
cd ${movieName}
ffmpeg -i ${movieName}.flv -acodec copy -vcodec copy ${movieName}.mp4
ffmpeg -i ${movieName}.mp4 -c copy -bsf:v h264_mp4toannexb -f mpegts ${movieName}.ts
ffmpeg -i ${movieName}.ts -c copy -map 0 -f segment -segment_list playlist.m3u8 -segment_time 10 ${movieName}%03d.ts
mv ${movieName}.ts ../
rm -f ${movieName}.flv
rm -f ${movieName}.mp4
echo "handler finish "${movieName}