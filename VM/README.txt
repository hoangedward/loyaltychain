Vagrant:
https://www.vagrantup.com/

Start VM with vagrant
> vagrant up

Login to VM
> vagrant ssh

Logout of VM
$ logout

Gracefully shutdown the VM (not remove)
> vagrant halt
(---- not half )

========================================================
Install Docker
https://docs.docker.com/install/linux/docker-ce/ubuntu/

$ sudo apt-get remove docker docker-engine docker.io

$ sudo apt-get update

$ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common

$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

$ sudo apt-key fingerprint 0EBFCD88

$ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
$ sudo apt-get update

$ sudo apt-get install docker-ce

$ docker version

========================================================
Install Docker Compose
https://docs.docker.com/compose/install/

$ sudo curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

$ sudo chmod +x /usr/local/bin/docker-compose

$ docker-compose --version


========================================================
Install Virtualbox
https://websiteforstudents.com/virtualbox-5-2-on-ubuntu-16-04-lts-server-headless/

========================================================
Install Docker machine
https://docs.docker.com/machine/install-machine/

base=https://github.com/docker/machine/releases/download/v0.14.0 &&
  curl -L $base/docker-machine-$(uname -s)-$(uname -m) >/tmp/docker-machine &&
  sudo install /tmp/docker-machine /usr/local/bin/docker-machine
  
docker-machine version

========================================================
create a Linux VM where we can run our containers
$ docker-machine create --driver virtualbox containerhost

$ eval "$(docker-machine env containerhost)"

Test
$ sudo docker run hello-world

========================================================
docker swarm
No need for now

========================================================
BigchainDB
$ sudo docker pull bigchaindb/bigchaindb:all-in-one

$ sudo docker run \
  --detach \
  --name bigchaindb \
  --publish 9984:9984 \
  --publish 9985:9985 \
  --publish 27017:27017 \
  --publish 26657:26657 \
  --volume $HOME/bigchaindb_docker/mongodb/data/db:/data/db \
  --volume $HOME/bigchaindb_docker/mongodb/data/configdb:/data/configdb \
  --volume $HOME/bigchaindb_docker/tendermint:/tendermint \
  bigchaindb/bigchaindb:all-in-one
  
9984 BigchainDB API server
9985 BigchainDB Websocket server
27017 Default port for MongoDB
26657 Tendermint RPC server
  

For later time
$ sudo docker start bigchaindb

Test
http://localhost:9984/

$ curl -L http://localhost:9984/


========================================================
Stop and remove the current local container host
$ docker-machine stop containerhost 
exit status 1 
$ docker-machine rm containerhost 
Successfully removed containerhost





