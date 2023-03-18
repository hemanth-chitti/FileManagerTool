import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title(title: any) {
    throw new Error('Method not implemented.');
  }
  cdrive: any;
  firstFolderOpenData: any;

  cOpenName!: string;

  extra: string = ":,";

  open2Name!: string;
  open2NameCarry!: string;
  open2path!:any;
  onSubmitName!:string;
  num!:number;
  rename!:string;



  constructor(private http: HttpClient) { }
  ngOnInit() {


  }

  visible: boolean = false;


  formsvisible: boolean =true;

  onclick() {
    this.visible = !this.visible;
  }

myTreeVisible:boolean=true;

  onSelect() 
  {
    
    this.opened=[];

    this.cOpenName = ((document.getElementById("copen") as HTMLInputElement).value);
    console.log(typeof (this.cOpenName));
    console.log(this.cOpenName);

    

    console.log(this.extra);

    this.cOpenName = this.cOpenName.concat(this.extra)

    console.log(this.cOpenName);

    let resp = this.http.get("http://localhost:8086/open/" + this.cOpenName);
    resp.subscribe((data) =>{ this.cdrive = data
      let value=this.cdrive;
      if(value.length==0)
      {
        alert("Folder Empty");
      }
    });

    

    this.visible1=false;

    this.myTreeVisible=false;

  }

  

  visible1: boolean = false;

  openVisible:boolean=false;






  opened: any[] = [];
  temArray: any = [];
  isElementExist: boolean = false;
  treePath:any[]=[];
  extensionCheck!:string;
  expandable:boolean= true;
  treeVisible:boolean=false;
  type!:string;
  onSelectSecond(slectedItem: any) {
    this.type=slectedItem.type;
    if(this.type=="File folder")
    {
      this.openVisible=true;
    }
    else
    {
      this.openVisible=false;
    }
    this.myTreeVisible=true;
    this.open2path=slectedItem.path;
     
     console.log(this.open2path);
      
      this.open2path=this.open2path.split("\\");

     
      console.log(this.open2path);

      this.extensionCheck=slectedItem.type;
    
      if(this.extensionCheck=="Text Document")
      {
        alert("Cannot Open a File! Open a Folder");
      }

    if(this.expandable){ 

      this.temArray = [];
      this.open2Name = slectedItem;
      this.open2NameCarry = slectedItem;
    if (this.opened.length === 0) {
      this.opened.push(this.open2NameCarry);
    }
    this.temArray = this.opened.filter(o => o === this.open2NameCarry);
    if (this.temArray.length === 0) {
      this.opened.push(this.open2NameCarry);
      console.log("Button Name" + this.opened);
    }
    else{
      let newIndex= this.opened.findIndex(x => x === this.open2Name)
      for(let index=0; index < this.opened.length ; index++)
      {
        if(index > newIndex){
          this.opened.pop();
          console.log("deleted" + this.opened);
        }
      }
    }
    }

    console.log(this.open2Name);
    

  }

  doubleClickPath:any;
  doubleClick(slectedItem: any)
    {
      this.visible=false;
      this.visible1 = true;
    this.expandable=true;
      this.doubleClickPath=slectedItem.path;
      this.doubleClickPath=this.doubleClickPath.split("\\");

      let resp = this.http.get("http://localhost:8086/open/" + this.doubleClickPath);
      resp.subscribe((data) => {this.firstFolderOpenData = data
       let value=this.firstFolderOpenData;
       if(value.length==0)
       {
         alert("Folder Empty");
       }
      });

    }

  renamevisible: boolean = false;
  compressvisible: boolean = false;

  onOpenClick()
    {
    this.visible1 = true;
   this.expandable=true;
    console.log(this.open2path);
    
    let resp = this.http.get("http://localhost:8086/open/" + this.open2path);
   resp.subscribe((data) => {this.firstFolderOpenData = data
    let value=this.firstFolderOpenData;
    if(value.length==0)
    {
      alert("Folder Empty");
    }
    });

    }
  
  


  onRenameClick() {
    this.expandable=false;
    this.renamevisible = !this.renamevisible
    this.compressvisible = false;
  }

  onCompressClick() {
    this.expandable=false;
    this.compressvisible = !this.compressvisible
    this.renamevisible = false;

  }

  onclick1()
  {
    this.visible=false;
  }

  r!:number;
 open2path1!:any;
 open2path2:any;
 dummyVariable!:string;
 onRenameOpenClick()
 {
 

  this.renamevisible=!this.renamevisible;
  this.onSubmitName=((document.getElementById('afteropen') as HTMLInputElement).value)
   
    let value=this.onSubmitName.trim();

    if(value.length==0)
    {
      this.dummyVariable='null';
      console.log(this.dummyVariable);
      this.onSubmitName=this.dummyVariable
      console.log(this.onSubmitName);
      
      
    }

    let resp = this.http.get("http://localhost:8086/rename/"+this.open2path+'/'+this.onSubmitName,{responseType:'text'});
    resp.subscribe((data)=>{this.rename=data
    alert(this.rename)});

    this.r=this.open2path.length;
    console.log(this.r)
    if(this.r==2){
    
      let resp1 = this.http.get("http://localhost:8086/open/"+this.cOpenName);
      resp1.subscribe((data)=>this.cdrive=data);
      
    }
    else{
      this.visible=false;
      this.open2path1=this.open2path;
      this.open2path2=this.open2path1.pop();
      console.log(this.open2path1);
      
      let resp2 = this.http.get("http://localhost:8086/open/"+this.open2path1);
      resp2.subscribe((data)=>this.firstFolderOpenData=data);
      
    }
    console.log(this.rename);
    console.log(this.open2path)

 }

 compressName!:string;
 compressData!:string;
 onCompressOpenClick()
 {
  
  this.compressvisible=!this.compressvisible;
  this.compressName=((document.getElementById('aftercompress') as HTMLInputElement).value)

  
  let value=this.compressName.trim();

  if(value.length==0)
  {
    this.dummyVariable='null';
    console.log(this.dummyVariable);
    this.compressName=this.dummyVariable
    console.log(this.compressName);
    
    
  }
    
    let compressResp = this.http.get("http://localhost:8086/compress/"+this.open2path+'/'+this.compressName,{responseType:'text'});
    compressResp.subscribe((data)=>{this.compressData=data
    alert(this.compressData)});

    this.r=this.open2path.length;
    console.log(this.r)
    if(this.r==2){
    
      let resp1 = this.http.get("http://localhost:8086/open/"+this.cOpenName);
      resp1.subscribe((data)=>this.cdrive=data);
      
    }
    else{
      this.visible=false;
      this.open2path1=this.open2path;
      this.open2path2=this.open2path1.pop();
      console.log(this.open2path1);
      
      let resp2 = this.http.get("http://localhost:8086/open/"+this.open2path1);
      resp2.subscribe((data)=>this.firstFolderOpenData=data);
      
    }
 }

 open2path3:any
 myR!:number;
 onBackClick(){

  this.open2path3=this.open2path;
  this.open2path3.pop();
  this.myR=this.open2path3.length;
    console.log(this.myR)
    if(this.myR==1){
    
      let resp5 = this.http.get("http://localhost:8086/open/"+this.cOpenName);
      resp5.subscribe((data)=>this.cdrive=data);
      
    }
  let resp4 = this.http.get("http://localhost:8086/open/"+this.open2path3);
  resp4.subscribe((data)=>this.firstFolderOpenData=data);
  

}




}
