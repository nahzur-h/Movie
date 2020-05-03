#!/bin/bash
handlerMovie() {
	ffmpeg -i ${1}.flv -acodec copy -vcodec copy ${1}.mp4
	ffmpeg -i ${1}.mp4 -c copy -bsf:v h264_mp4toannexb -f mpegts ${1}.ts
	ffmpeg -i ${1}.ts -c copy -map 0 -f segment -segment_list playlist.m3u8 -segment_time 10 ${1}%03d.ts

	mv ${1}.ts ../
	rm -f ${1}.flv
	rm -f ${1}.mp4
}

movieName=$1
readonly movieName

sourceDir="movie-source"
cd ${sourceDir}

mkdir ${movieName}
mv ${movieName}.flv ${movieName}
cd ${movieName}

handlerMovie ${movieName}
echo "handler finish "${movieName}