/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import RentalPlanService from '@/entities/rental-plan/rental-plan.service';
import { RentalPlan } from '@/shared/model/rental-plan.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('RentalPlan Service', () => {
    let service: RentalPlanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new RentalPlanService();
      currentDate = new Date();
      elemDefault = new RentalPlan(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
            expirationDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RentalPlan', async () => {
        const returnedFromService = Object.assign(
          {
            city: 'BBBBBB',
            neighborhood: 'BBBBBB',
            userName: 'BBBBBB',
            userEmail: 'BBBBBB',
            startDate: dayjs(currentDate).format(DATE_FORMAT),
            endDate: dayjs(currentDate).format(DATE_FORMAT),
            rentalConfirmationNumber: 'BBBBBB',
            cardNumber: 'BBBBBB',
            cardVerificationValue: 'BBBBBB',
            expirationDate: dayjs(currentDate).format(DATE_FORMAT),
            confirmation: true,
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startDate: currentDate,
            endDate: currentDate,
            expirationDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RentalPlan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
