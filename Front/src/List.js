import React from 'react';
import {Todo} from './Todo';



export class List extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const cList = this.props.cList.map((task, i) => {
            return (
                <div key={i}>
                    <Todo description={task.description}  responsible={task.responsible} status={task.status} dueDate={task.dueDate} img={task.fileUrl ? task.fileUrl : ""}></Todo>
                </div>
                
            )
        });

        return (
            
            <div className="cardContainer">
                {cList}
            </div>

        );
    }
    
}