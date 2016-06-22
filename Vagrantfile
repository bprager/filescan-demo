# This guide is optimized for Vagrant 1.7 and above.
# Although versions 1.6.x should behave very similarly, it is recommended
# to upgrade instead of disabling the requirement below.
Vagrant.require_version ">= 1.7.0"

Vagrant.configure(2) do |config|

 # create search box
  config.vm.define :search do |search_config|
    search_config.vm.box = "ubuntu/trusty64"
    search_config.vm.hostname = "search"
    search_config.vm.network :private_network, ip: "10.0.15.10"
    config.vm.network "forwarded_port", guest: 9201, host: 9201
    config.vm.network "forwarded_port", guest: 9301, host: 9301
    config.vm.synced_folder "data/", "/opt/elasticsearch/data"
    config.vm.synced_folder "logs/", "/opt/elasticsearch/logs"
    search_config.vm.provider "virtualbox" do |vb|
        vb.memory = "512"
    end

    # Disable the new default behavior introduced in Vagrant 1.7, to
    # ensure that all Vagrant machines will use the same SSH key pair.
    # See https://github.com/mitchellh/vagrant/issues/5005
    search_config.ssh.insert_key = false

    search_config.vm.provision "ansible" do |ansible|
        ansible.verbose = "v"
        ansible.playbook = "elasticsearch.yml"
    end
  end
end
