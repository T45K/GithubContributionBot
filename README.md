# GithubContributionBot
日付が変更した瞬間にその日の Github でのコントリビューション数を Twitter でお知らせします

## 必要なもの
- Twitter アカウント
- Github アカウント
- 24時間365日稼働しているパソコン
- Java 8

## 手順
1. [ここ](https://developer.twitter.com/)からツイ垢を登録して API 関連を入手してください
2. [ここ](https://github.com/T45K/GithubContributionBot/releases)からこのプロジェクトの jar を落としてください
3. プロパティーファイルを設定してください<br>
設定項目は表の通りです
```shell
$ vi hoge.properties
userName=...
apiKey=...
```

4. 24時間365日稼働しているパソコンで実行しましょう<br>
引数にプロパティーファイルのパスを与えてください
```shell
nohup java -jar huga.jar ./hoge.properties
```

## プロパティーファイルの設定項目
以下のように設定してください

|key|value|
|---|:---|
|userName|Github に登録している名前<br>https://github.com/○○○ の○○○の部分|
|apiKey|Consumer API keys の API key|
|apiSecret|Consumer API Keys の API secret key|
|token|Access token & access token secret の Access token|
|tokenSecret|Access token & access token secret の Access token secret|
