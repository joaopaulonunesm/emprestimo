# Criar o tópico SNS
aws --endpoint-url=http://localhost:4566 sns create-topic --name sns-emprestimo

# Fila de análise de crédito
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-analise-credito
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-analise-credito-dlq
DLQ_CREDITO_ARN=$(aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-analise-credito-dlq --attribute-name QueueArn --query 'Attributes.QueueArn' --output text)
aws --endpoint-url=http://localhost:4566 sqs set-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-analise-credito --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"'"$DLQ_CREDITO_ARN"'\",\"maxReceiveCount\":\"5\"}"}'
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:sa-east-1:000000000000:sns-emprestimo --protocol sqs --notification-endpoint arn:aws:sqs:sa-east-1:000000000000:sqs-emprestimo-analise-credito --attributes "RawMessageDelivery=true"

# Fila de análise de garantia
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-analise-garantia
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-analise-garantia-dlq
DLQ_GARANTIA_ARN=$(aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-analise-garantia-dlq --attribute-name QueueArn --query 'Attributes.QueueArn' --output text)
aws --endpoint-url=http://localhost:4566 sqs set-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-analise-garantia --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"'"$DLQ_GARANTIA_ARN"'\",\"maxReceiveCount\":\"5\"}"}'
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:sa-east-1:000000000000:sns-emprestimo --protocol sqs --notification-endpoint arn:aws:sqs:sa-east-1:000000000000:sqs-emprestimo-analise-garantia --attributes "RawMessageDelivery=true"

# Fila de análise de risco
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-analise-risco
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-analise-risco-dlq
DLQ_RISCO_ARN=$(aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-analise-risco-dlq --attribute-name QueueArn --query 'Attributes.QueueArn' --output text)
aws --endpoint-url=http://localhost:4566 sqs set-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-analise-risco --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"'"$DLQ_RISCO_ARN"'\",\"maxReceiveCount\":\"5\"}"}'
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:sa-east-1:000000000000:sns-emprestimo --protocol sqs --notification-endpoint arn:aws:sqs:sa-east-1:000000000000:sqs-emprestimo-analise-risco --attributes "RawMessageDelivery=true"

# Fila de simulação
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-simulacao
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-emprestimo-simulacao-dlq
DLQ_SIMULACAO_ARN=$(aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-simulacao-dlq --attribute-name QueueArn --query 'Attributes.QueueArn' --output text)
aws --endpoint-url=http://localhost:4566 sqs set-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-emprestimo-simulacao --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"'"$DLQ_SIMULACAO_ARN"'\",\"maxReceiveCount\":\"5\"}"}'
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:sa-east-1:000000000000:sns-emprestimo --protocol sqs --notification-endpoint arn:aws:sqs:sa-east-1:000000000000:sqs-emprestimo-simulacao --attributes "RawMessageDelivery=true"

aws dynamodb create-table --table-name tb_simulacao \
    --attribute-definitions AttributeName=id_simulacao,AttributeType=S \
    --key-schema AttributeName=id_simulacao,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --endpoint-url=http://localhost:4566