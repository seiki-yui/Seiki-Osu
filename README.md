# Seiki-Osu
> 一个简单的运行在 `Mirai-Console` 的 OSU 图标生成器插件。

## 如何配置
> 该项目需要 [`SkikoMirai`](https://github.com/LaoLittle/SkikoMirai) 作为前置插件，请提前安装。
- 1.按照 [`SkikoMirai`](https://github.com/LaoLittle/SkikoMirai) 的说明操作。
- 2.在 [`Release`](https://github.com/xiao-zheng233/Seiki-Osu/releases) 里下载本插件与所需的字体文件，
- - 插件放在 `plugins` 文件夹
- - 字体放在 `data/org.laolittle.plugin.SkikoMirai/Fonts` 文件夹。*如果没有这个文件夹，请看看是不是第一步哪里出错了。*
- 3.启动 `Mirai-Console` ，Go!

~~*由于前置插件 `SkikoMirai` 兼容性问题，插件可能在 `Windows7`系统报错、不起作用。*~~

## 如何使用
> 该项目暂未考虑支持 `Command` 系统，因为很多时候用户并不懂得如何配置权限，故使用正则匹配，满足要求就会触发指令。
- 发送 `#osu osu!`生成原汁原味的 `osu!` 图标；
- 发送 `#osu omg!`生成 `omg!` 的 `osu!` 图标。
