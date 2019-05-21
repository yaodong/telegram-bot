# Telegram Bot

[![Build Status](https://travis-ci.org/yaodong/telegram-bot.svg?branch=master)](https://travis-ci.org/yaodong/telegram-bot)

## Requirements

This bot is dependent on the following AWS services:

- DynamoDB for persistence
- SQS for queue messages
- SES for sending emails

### Environment Variables

| Name                  |
| --------------------- |
| AWS_REGION            |
| AWS_ACCESS_KEY_ID     |
| AWS_SECRET_ACCESS_KEY |

### Add task to Things

Use command `/task` to add a task to [Things](https://culturedcode.com/things/).

**Example**:

```text
/task remember the milk
```

## Deployment

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)
