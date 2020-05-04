#!/bin/bash
handlerMovie() {
	ffmpeg -i ${1}.flv -acodec copy -vcodec copy ${1}.mp4
	ffmpeg -i ${1}.mp4 -c copy -bsf:v h264_mp4toannexb -f mpegts ${1}.ts
	ffmpeg -i ${1}.ts -c copy -map 0 -f segment -segment_list playlist.m3u8 -segment_time 10 ${1}%03d.ts

	mv ${1}.ts ../
	rm -f ${1}.flv
	rm -f ${1}.mp4
}

handlerMovieDir() {
	tagMovieName=${1}
	sourceDir="${dir}"
	tagDir="../../home/${tagMovieName}"
	mkdir ${tagDir}
	sourceFileList=`ls ${sourceDir}`
	for element in ${sourceFileList}
	do
		mv ${element} ${tagDir}
	done
	cd ..
	rmdir ${tagMovieName}
}

handlerGitPush() {
	contentText=${1}
	cd ..
	git add .
	git commit -m "add ${contentText} ts set"
	git fetch origin
	git rebase origin/master
	git push origin master:master
}

movieName=$1
readonly movieName

sourceDir="movie-source"
cd ${sourceDir}

mkdir ${movieName}
mv ${movieName}.flv ${movieName}
cd ${movieName}

handlerMovie ${movieName}
handlerMovieDir ${movieName}
handlerGitPush ${movieName}
echo "handler finish "${movieName}