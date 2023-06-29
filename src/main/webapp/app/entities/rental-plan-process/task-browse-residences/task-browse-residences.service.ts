import axios from 'axios';
import { TaskBrowseResidencesContext } from './task-browse-residences.model';

const baseApiUrl = 'api/rental-plan-process/task-browse-residences';

export default class TaskBrowseResidencesService {
  public loadContext(taskId: number): Promise<TaskBrowseResidencesContext> {
    return new Promise<TaskBrowseResidencesContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskBrowseResidencesContext> {
    return new Promise<TaskBrowseResidencesContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskBrowseResidencesContext: TaskBrowseResidencesContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskBrowseResidencesContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
