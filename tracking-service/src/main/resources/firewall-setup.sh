#!/bin/bash

# Set up iptables firewall rules
# This rule blocks incoming TCP connections to port 8081 from Docker's internal network range

echo "Setting up firewall rules..."

iptables -A INPUT -i lo -j ACCEPT

iptables -A INPUT -p tcp --dport 8080 -j ACCEPT

iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# Block connections to port 8081 from Docker's internal networks
# This simulates a firewall blocking inter-service communication
iptables -A INPUT -p tcp --dport 8081 -s 172.16.0.0/12 -j DROP

iptables -A INPUT -p tcp --dport 8081 -j ACCEPT


echo "Firewall rules applied:"
iptables -L -n
