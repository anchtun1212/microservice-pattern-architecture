## Script

Execute: `01-postgres-schema.sql`

## Important Links
- Apache Kafka - https://kafka.apache.org/
- Consul - https://www.consul.io/
- Debezium - https://debezium.io/
- Axon - https://www.axoniq.io/
- Redis - http://redis.io/
- yauaa (User-Agent Analyzer) - https://yauaa.basjes.nl/
- Vault by HashiCorp - https://www.vaultproject.io/
- Install Vault - https://developer.hashicorp.com/vault/downloads?product_intent=vault
- Sentry - https://sentry.io/

## Some Commands

|     Command       |     Description          |
| ------------- | ------------- |
| "mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=9993'" | How to configure port for a Spring Boot application on Runtime |
| "sudo vault server -config=/etc/vault.d/vault.hcl" | Start Vault |

# Debezium Config
      /etc/postgresql/14/main/postgresql.conf
      SHOULD BE:
      # Debezium
      wal_level = logical             
      max_wal_senders = 5             
      max_replication_slots = 5
      listen_addresses = '*'
      search_path = '"$user",public'
