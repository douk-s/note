git clone		复制

git add		添加

git commit		提交本地

git commit -a -m "注释"		添加并提交

git push		提交服务

git pull		拉取服务

![1680511152826](D:\note\git\mdimg\1680511152826.png)

文件还在本地 Untracked -未追踪状态

 已追踪状态stage

![1680513516873](D:\note\git\mdimg\1680513516873.png)

git fetch		更新到本地

git diff		对比

git status		查看状态

git log		查看提交的版本

可以创建 .gitignore文件

touch . gitignore	把不提交的文件名写进去，commit的时候就不会提交

git branch (分支名)		添加新的分支，没有分支名就是查看所有分支

git branch -d 分支名		删除分支，会提示有没有提交到主分支

git branch -D 分支名		直接删除

git checkouk （分支名）		切换分支名

git checkouk -b 分支名		创建并切换到分支

git merge 其他分支名		把其他分支合并过来