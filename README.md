<h3>Instruções para backend e frontend SysaABA</h3>

<h5>Deploy BACKEND</h5>
Acessar EC2
```
ssh -i "sysaba-ec2.pem" ubuntu@ADICONE-IP-AQUI
```

Enviar jar para EC2
```
scp -i sysaba-ec2.pem sysaba-1.0.0.jar ubuntu@ADICONE-IP-AQUI:/home/ubuntu
```

Iniciar o processo
```
sudo systemctl start syaba-java.service
```

Parar o processo
```
sudo systemctl stop syaba-java.service
```

Habilitar o processo para subir no momento do boot da maquina
```
sudo systemctl enable syaba-java.service
```

Verificar o status do processo
```
sudo systemctl status syaba-app.service
```

Profile local. Configurar essa opção no InteliJ
```
spring.profiles.active=local
```
<br/>

<h5>Deploy FRONTEND</h5>

Executar localmente antes de build no Netlify
```
quasar build --mode pwa
```

Executar a aplicação
```
quasar dev
```