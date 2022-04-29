import tweepy
from datetime import timedelta

CK = 'jtDcElpBQD4WTlToGAoF3hm9k'                             # Consumer Key
CS = 'E9tPTOqYH2Of2TRfVe6oEdtCujWo8B7xnkn7llfr95CkAAuJKw'     # Consumer Secret
AT = '1477546397750931458-X2alf0FTaFS2OjAy7jbygjZVSPB88A' # Access Token
AS = 'YqEjsNzIqAaMSodPaxO63ZqCxA0UZNcKDFB2U1eYcNLqq'         # Accesss Token Secert

#認証情報を設定
auth=tweepy.OAuthHandler(CK, CS)
auth.set_access_token(AT, AS)

#APIインスタンスの作成
api=tweepy.API(auth)

Account = 'sbg_news'

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