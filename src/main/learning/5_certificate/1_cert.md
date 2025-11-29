# CERT

# CA certificate

elasticsearch-certutil ca --out "D:\Study\elastic_logs\cert\elastic-stack-ca.p12" --pass ""

# Step 2: Generate HTTP certificate (http.p12)

elasticsearch-certutil http --out "D:\Study\elastic_logs\cert\http.p12" --pass ""
