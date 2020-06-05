function Galeria(container, gallery){

	var self = this;
	this.container = container;
	this.gallery = gallery;
	this.images = [];
	this.buttons = [];
	this.current = 0;
	this.interval = false;
	this.fade = false;//TODO: implement or remove

	this.cnf = {"frames":5, "transition":50, "viewTime":2500};

	this.fadeIn = function(element){
		var op = element.style.opacity*this.cnf.frames;
		element.style.opacity = (++op)/this.cnf.frames;
		this.fade = op < this.cnf.frames ? setTimeout(function(){self.fadeIn(element);}, self.cnf.transition) : false ;};

	this.select = function(index){//TODO: (if current == index)
//		clearTimeout(this.fade);
		this.buttons[this.current].className = '';
		this.buttons[index].className = 'selected';
		if(index >= this.current){
			this.fadeIn(this.images[index]);}
		else{//TODO: reverse
			for(var x = 0; x<this.gallery.length; this.images[x].style.opacity = (x == index || x == this.current) ? 1 : 0, x++);
			this.fadeOut(this.images[this.current]);}};

	this.fadeOut = function(element){
		var op = element.style.opacity*this.cnf.frames;
		element.style.opacity = (--op)/this.cnf.frames;
		this.fade = op > 0 ? setTimeout(function(){self.fadeOut(element);}, self.cnf.transition) : false ;};

	this.rotate = function(){
		var next = this.current+1 == this.gallery.length ? 0 : this.current+1;
		this.select(next);
		this.current = next;};

	this.clickButton = function(index){
		clearInterval(this.interval);
		this.select(index);
		this.current = index;
		this.interval = setInterval(function(){self.rotate();}, self.cnf.viewTime);};


	(function(){//pseudo constructor, creates li's for buttons and layered img's
		var ul = document.createElement('ul');
		for(var x = 0; x < self.gallery.length; x++){
			self.images[x] = document.createElement('img');
			self.images[x].src = self.gallery[x];
			self.images[x].style.zIndex = 10+x;
			self.images[x].style.opacity = 0;
			self.buttons[x] = document.createElement('li');
			self.buttons[x].innerHTML = x+1;
			self.buttons[x].onclick = function(){self.clickButton(this.innerHTML-1);}
			ul.appendChild(self.buttons[x]);
			self.container.appendChild(self.images[x]);}
		self.container.appendChild(ul);
		self.select(0);
		self.interval = setInterval(function(){self.rotate();}, self.cnf.viewTime);})();}
