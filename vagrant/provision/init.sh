#!/usr/bin/env bash

function step {
  echo -e "\033[01;34m -- $1 --\033[00m";
}

step "Initial sequence start."

step "Update OS"
apt-get update
apt-get upgrade -y

step "Install additional tools"
apt-get install htop -y
apt-get install mc -y
apt-get install make -y

step "Add postgres repository"
echo "deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main" > /etc/apt/sources.list.d/pgdg.list
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add -
apt-get update

step "Install postgres"
apt-get install postgresql-9.6 postgresql-common postgresql-client-9.6 postgresql-server-dev-9.6  postgresql-contrib-9.6 -y
cp /home/ubuntu/api/vagrant/postgres/.pgpass /root/.pgpass
cp /home/ubuntu/api/vagrant/postgres/fts* /usr/lib/postgresql/9.6/lib/
chmod 0600 /root/.pgpass
#enable to connect from host machine through forwarded port
sed -i -e "s/#listen_addresses = 'localhost'/listen_addresses = '*'/g" /etc/postgresql/9.6/main/postgresql.conf
#grant pgsql to all
echo "host all all 0.0.0.0/0 trust" >> /etc/postgresql/9.6/main/pg_hba.conf
service postgresql restart

step "Install JDK 1.8"
apt-get install default-jdk -y

echo "JAVA_HOME=\"/usr/lib/jvm/java-8-openjdk-amd64\"" >> /etc/environment
source /etc/environment

step "Install Jetty"
mkdir /home/ubuntu/jetty
cd /home/ubuntu/jetty
wget http://repo1.maven.org/maven2/org/eclipse/jetty/jetty-distribution/9.3.12.v20160915/jetty-distribution-9.3.12.v20160915.tar.gz
tar -xvf jetty-distribution-9.3.12.v20160915.tar.gz
ln -s jetty-distribution-9.3.12.v20160915 jetty
rm jetty-distribution-9.3.12.v20160915.tar.gz

step "Install gradle"
wget https://services.gradle.org/distributions/gradle-3.5-bin.zip
unzip -d /usr/share gradle-3.5-bin.zip
ln -s /usr/share/gradle-3.5/bin/gradle /usr/bin/gradle
rm gradle-3.5-bin.zip
