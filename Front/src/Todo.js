import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import { Container } from '@material-ui/core';
import Link from '@material-ui/core/Link';
import PictureAsPdfIcon from '@material-ui/icons/PictureAsPdf';

export class Todo extends React.Component{
  render(){
    return (
      <Container component="main" maxWidth="sm" fixed= "true">
        <Card style={{marginTop: "10px"}}>
            <CardContent>
                <p>{this.props.description}</p>
                <p>{this.props.status} - {this.props.dueDate.format('DD-MM-YYYY')} </p>
                <p>{this.props.responsible.name}</p>
                { this.props.img.slice(-3)=="pdf" ? <div> <Link href={"http://localhost:8080/api/"+this.props.img}>  Download <PictureAsPdfIcon></PictureAsPdfIcon></Link></div> : <img src={"http://localhost:8080/api/"+this.props.img} width="500" height="500"/>}
            </CardContent>
        </Card>
      </Container>
    );
  }
} 