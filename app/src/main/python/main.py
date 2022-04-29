import tweepy
from datetime import timedelta

CK = ''                             # Consumer Key
CS = ''     # Consumer Secret
AT = '' # Access Token
AS = ''         # Accesss Token Secert

#認証情報を設定
auth=tweepy.OAuthHandler(CK, CS)
auth.set_access_token(AT, AS)

#APIインスタンスの作成
api=tweepy.API(auth)

Account = '' #twitter id

tweet_data = ""
tweet_date = ""
num = 0

tweets = api.user_timeline(id=Account, count=1)
for tweet in tweets:
    tweet.created_at += timedelta(hours=9)
    num += 1
    tweet_data=tweet.text
    tweet_date=tweet.created_at

def date():
    temp = str(tweet_date)
    return temp[:-15]

def data():
    return tweet_data