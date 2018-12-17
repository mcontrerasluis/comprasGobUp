/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmbarqueComponentsPage, EmbarqueDeleteDialog, EmbarqueUpdatePage } from './embarque.page-object';

const expect = chai.expect;

describe('Embarque e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let embarqueUpdatePage: EmbarqueUpdatePage;
    let embarqueComponentsPage: EmbarqueComponentsPage;
    let embarqueDeleteDialog: EmbarqueDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Embarques', async () => {
        await navBarPage.goToEntity('embarque');
        embarqueComponentsPage = new EmbarqueComponentsPage();
        expect(await embarqueComponentsPage.getTitle()).to.eq('comprasGobUpApp.embarque.home.title');
    });

    it('should load create Embarque page', async () => {
        await embarqueComponentsPage.clickOnCreateButton();
        embarqueUpdatePage = new EmbarqueUpdatePage();
        expect(await embarqueUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.embarque.home.createOrEditLabel');
        await embarqueUpdatePage.cancel();
    });

    it('should create and save Embarques', async () => {
        const nbButtonsBeforeCreate = await embarqueComponentsPage.countDeleteButtons();

        await embarqueComponentsPage.clickOnCreateButton();
        await promise.all([
            embarqueUpdatePage.setCodigoRastreoInput('codigoRastreo'),
            embarqueUpdatePage.setFechaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            embarqueUpdatePage.setDetallesInput('detalles'),
            embarqueUpdatePage.facturaSelectLastOption()
        ]);
        expect(await embarqueUpdatePage.getCodigoRastreoInput()).to.eq('codigoRastreo');
        expect(await embarqueUpdatePage.getFechaInput()).to.contain('2001-01-01T02:30');
        expect(await embarqueUpdatePage.getDetallesInput()).to.eq('detalles');
        await embarqueUpdatePage.save();
        expect(await embarqueUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await embarqueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Embarque', async () => {
        const nbButtonsBeforeDelete = await embarqueComponentsPage.countDeleteButtons();
        await embarqueComponentsPage.clickOnLastDeleteButton();

        embarqueDeleteDialog = new EmbarqueDeleteDialog();
        expect(await embarqueDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.embarque.delete.question');
        await embarqueDeleteDialog.clickOnConfirmButton();

        expect(await embarqueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
