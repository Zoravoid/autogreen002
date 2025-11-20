FROM node:22-alpine

WORKDIR /app

COPY package.json ./

RUN npm install

COPY . .

EXPOSE 3000 

CMD [ "npm", "run", "dev" ]

#first build the docker image
#docker build -t zoravoid/autogreen-frontend:01 .
#second build the container with the docker run command tested and working
#docker run -d --name autogreen_test -p 3000:3000 zoravoid/autogreen-frontend:01