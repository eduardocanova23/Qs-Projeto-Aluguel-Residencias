import axios from 'axios';
import { BookReservationTaskContext } from './book-reservation-task.model';

const baseApiUrl = 'api/rental-plan-process/book-reservation-task';

export default class BookReservationTaskService {
  public loadContext(taskId: number): Promise<BookReservationTaskContext> {
    return new Promise<BookReservationTaskContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<BookReservationTaskContext> {
    return new Promise<BookReservationTaskContext>((resolve, reject) => {
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

  public complete(bookReservationTaskContext: BookReservationTaskContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, bookReservationTaskContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
