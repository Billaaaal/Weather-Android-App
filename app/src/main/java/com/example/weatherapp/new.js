import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View, TextInput, ScrollView, Image } from 'react-native';
import { backgroundColor } from 'react-native/Libraries/Components/View/ReactNativeStyleAttributes';



//create a react nat

var request = new XMLHttpRequest();
request.onreadystatechange = (e) => {
  if (request.readyState !== 4) {
    return;
  }

  if (request.status === 200) {
    //console.log('success', request.responseText);
  } else {
    //console.warn('error');
  }
};


const task = [
  {
    title: "Morning Walk",
    thumbnail: "https://i3.ytimg.com/vi/vCA7q-GdjhU/maxresdefault.jpg",
    key: "1",
  },
  {
    title: "Morning Walk",
    thumbnail: "https://i3.ytimg.com/vi/vCA7q-GdjhU/maxresdefault.jpg",
    key: "2",
  },
  {
    title: "Morning Walk",
    thumbnail: "https://i3.ytimg.com/vi/vCA7q-GdjhU/maxresdefault.jpg",
    key: "3",
  }
  ];


request.open('GET', 'https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&order=market_cap_desc&per_page=1&page=1&sparkline=true');
request.send();

export default function App() {
  const [input, setInput] = React.useState("");
  return (
    
    <View style={styles.container}>

      

      
      
      <ScrollView style={styles.scroll}>

      <View style={{backgroundColor:"#ffffff", height:75, backgroundColor:"#202020"}}>
      <Image style={{top:35, zIndex: 7, width:130, height:50, left:0}}source={{uri: 'https://i.ibb.co/7171M0x/Sans-tiaeztre-removebg-preview.png'}}></Image>
      </View>
      <ScrollView style={{backgroundColor:"#ffffff", height:100}}>
      <Image style={{height:60, width:60, zIndex: 19, overflow: 'hidden', borderRadius: 40,marginLeft:10, marginTop:20}}source={{uri: 'https://yt3.ggpht.com/ytc/AKedOLQKm3IdxW5CbfCsfqgZf8EooZbebtsR0aWFjVUG=s88-c-k-c0x00ffffff-no-rj'}}></Image>
      <Image style={{height:60, width:60, zIndex: 19, overflow: 'hidden', borderRadius: 40, marginLeft:90, marginTop:-60}}source={{uri: 'https://yt3.ggpht.com/ytc/AKedOLQKm3IdxW5CbfCsfqgZf8EooZbebtsR0aWFjVUG=s88-c-k-c0x00ffffff-no-rj'}}></Image>

      </ScrollView>

      
      
      
      <Image style={{height:40, width:40, zIndex: 5, top:230, position:'absolute', overflow: 'hidden', borderRadius: 40,left:10}}source={{uri: 'https://yt3.ggpht.com/ytc/AKedOLQKm3IdxW5CbfCsfqgZf8EooZbebtsR0aWFjVUG=s88-c-k-c0x00ffffff-no-rj'}}></Image>


        {task.map(task => (
          <View key = {task.key} style={{backgroundColor:"#212121",height:305, borderBottomColor:'#383838',borderBottomWidth:1}}>
          <Image style={{height:40, width:40, zIndex: 5, top:230, position:'absolute', overflow: 'hidden', borderRadius: 40,left:10}}source={{uri: 'https://yt3.ggpht.com/ytc/AKedOLQKm3IdxW5CbfCsfqgZf8EooZbebtsR0aWFjVUG=s88-c-k-c0x00ffffff-no-rj'}}></Image>
          <Image style={{height:220}}source={{uri:task.thumbnail}}></Image>
          <Text style={{color:"#ffffff", marginLeft:40, marginTop:8, fontFamily:'Roboto', fontWeight:"bold", width:300, left:20}}>{task.title}</Text>
          <Text style={{color:"#909090", marginLeft:40, marginTop:2, fontFamily:'Roboto', fontWeight:"normal", width:300, left:20, fontSize:12}}>CulturePSG • 180 spectateurs</Text>
        </View>
        ))}

      </ScrollView>

      
      
    </View>
  );
}




/*<View style={{backgroundColor:"#212121",height:305, borderBottomColor:'#383838',borderBottomWidth:1}}>
          <Image style={{height:35, width:35, zIndex: 5, top:230, position:'absolute', overflow: 'hidden', borderRadius: 40,left:10}}source={{uri: 'https://yt3.ggpht.com/ytc/AKedOLQKm3IdxW5CbfCsfqgZf8EooZbebtsR0aWFjVUG=s88-c-k-c0x00ffffff-no-rj'}}></Image>
          <Image style={{height:220}}source={{uri: 'https://i.ytimg.com/vi/vCA7q-GdjhU/hq720_live.jpg?sqp=CJSP0pIG-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCUYy80jD4OhgAC23dHu_brMPU9Rw'}}></Image>
          <Text style={{color:"#ffffff", marginLeft:40, marginTop:8, fontFamily:'Roboto', fontWeight:"bold", width:300, left:20}}>Podcast 11/04/22 : Clermont/PSG (1-6) et questions/réponses</Text>
          <Text style={{color:"#909090", marginLeft:40, marginTop:2, fontFamily:'Roboto', fontWeight:"normal", width:300, left:20, fontSize:12}}>CulturePSG • 180 spectateurs</Text>
        </View>*/




const styles = StyleSheet.create({
  box:{
    height:40,
    backgroundColor:"#008b8b",
    borderColor:"#ffffff",
    borderWidth:0.5,
    

  },
  scroll:{

    /*backgroundColor:'#f05680',*/

  },
  container: {
    top:0,
    flex:1,
    backgroundColor: '#202020',
  },
  input: {
    fontSize:30,
    top:300,
    textAlign:'center',
  },
  text: {
    fontSize:30,
    top:100,
    textAlign:'center',
  },
  textApi: {
    fontSize:30,
    color:'#ffffff',
    textAlign:'center',
  },
  welcome: {
    flex: 1,
    margin: 20,
    backgroundColor: 'orange',
    margin: 10,
    textAlign: 'center',
    fontSize: 20,
    paddingTop: 70,
  },

});